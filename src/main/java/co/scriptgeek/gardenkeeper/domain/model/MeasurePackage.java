package co.scriptgeek.gardenkeeper.domain.model;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Instant;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.IntStream;

import static org.hibernate.validator.internal.util.CollectionHelper.newArrayList;

/**
 * Created by xiaofeng on 15/4/7.
 */
public class MeasurePackage {
    private ChannelSet channelSet;
    private DateTime packageTime;
    private List<MeasureLine> measureLines;
    private Duration dwell;

    public MeasurePackage(ChannelSet channelSet, DateTime lastPackageTime) {
        this.channelSet = channelSet;
        this.packageTime = lastPackageTime;
        this.measureLines = newArrayList();
        this.dwell = Duration.millis(0);
    }

    public boolean packable(ChannelId channelId) {
         return this.channelSet.getChannelSetId().equals(channelId);
    }

    public void packing(ChannelId channelId, Instant timestamp, float measureValue) {
        if (!packable(channelId)) {
            return;
        }

        final Optional<MeasureLine> matchedLine = measureLines.stream()
                .filter(timestamp::equals)
                .findFirst();

        if (matchedLine.isPresent()) {
            matchedLine.get().addMeasureValue(channelId, measureValue);
            return;
        }

        this.measureLines.add(
                new MeasureLine(timestamp, new Float[channelSet.getChannelCount()]));
        this.dwell = dwell.plus(timestamp.getMillis() - this.packageTime.getMillis());
        this.packageTime = timestamp.toDateTime();
    }

    public Float[] getCurrentMeasureValues() {
        return measureLines.get(measureLines.size() - 1).getMeasureValues();
    }

    public Float[] calcMinMeasureValues() {
        return IntStream.range(0, measureLines.size())
                .mapToObj(index -> measureLines.stream()
                        .map(measureLine -> measureLine.getMeasureValues()[index])
                        .min(Float::compare))
                .toArray(Float[]::new);
    }

    public Float[] calcMaxMeasureValues() {
        return IntStream.range(0, measureLines.size())
                .mapToObj(index -> measureLines.stream()
                        .map(measureLine -> measureLine.getMeasureValues()[index])
                        .max(Float::compare))
                .toArray(Float[]::new);
    }

    public Float[] calcAvgMeasureValues() {
        return IntStream.range(0, measureLines.size())
                .mapToObj(index -> measureLines.stream()
                        .mapToDouble(measureLine -> measureLine.getMeasureValues()[index])
                        .average().getAsDouble())
                .map(Double::floatValue)
                .toArray(Float[]::new);
    }

    public byte[] getTimestampList() {
        final long[] timestamp = this.measureLines.stream()
                .mapToLong(measureLine -> measureLine.getTimestamp().getMillis())
                .toArray();

        final ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES * timestamp.length);
        Arrays.stream(timestamp)
                .forEach(byteBuffer::putLong);
        return byteBuffer.array();
    }

    public byte[] getMeasureValuesMatrix() {
        final Float[] values = this.measureLines.stream()
                .flatMap(measureLine -> Arrays.stream(measureLine.getMeasureValues()))
                .toArray(Float[]::new);

        final ByteBuffer byteBuffer = ByteBuffer.allocate(Float.BYTES * values.length);
        Arrays.stream(values)
                .forEach(byteBuffer::putFloat);
        return byteBuffer.array();
    }

    public int getMeasureLineCount() {
        return this.measureLines.size();
    }

    public DateTime getPackageTime() {
        return packageTime;
    }
}
