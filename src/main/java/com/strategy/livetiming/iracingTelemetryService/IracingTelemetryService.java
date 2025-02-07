package com.strategy.livetiming.iracingTelemetryService;

import com.joffrey.iracing.irsdkjava.IRacingLibrary;
import com.joffrey.iracing.irsdkjava.telemetry.model.TelemetryData;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class IracingTelemetryService {

    private final IRacingLibrary iRacingLibrary;

    @PostConstruct
    public void startListening() {
        new Thread(() -> {
            while (true) {
                if (iRacingLibrary.getTelemetryData() != null) {
                    Flux<TelemetryData> telemetryStream = iRacingLibrary.getTelemetryData();

                    telemetryStream.subscribe(telemetryData -> {
                        System.out.println("RAW Telemetry Data: " + telemetryData.toString());
                    }, error -> {
                        System.err.println("Error receiving telemetry data: " + error.getMessage());
                    });

                    break; // Exit loop once telemetry starts
                } else {
                    System.out.println("Waiting for iRacing telemetry data...");
                }
                try {
                    Thread.sleep(2000); // Check every 2 seconds
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}