package pl.wsb.szprycha.intake.entity;

import jakarta.persistence.*;

@Embeddable
public class BikeInfo {

    @Column(name = "bike_brand", length = 40)
    private String brand;

    @Column(name = "bike_model", length = 60)
    private String model;

    @Column(name = "frame_number", length = 30)
    private String frameNumber;

    protected BikeInfo() {
    }

    public BikeInfo(String brand, String model, String frameNumber) {
        this.brand = brand;
        this.model = model;
        this.frameNumber = frameNumber;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getFrameNumber() {
        return frameNumber;
    }
}
