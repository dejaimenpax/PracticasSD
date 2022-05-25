package es.Group3.BiciURJC.model;

public class CapsulaIds {
    private Long user_id;
    private Long bicycle_id;
    private Long station_id;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getBicycle_id() {
        return bicycle_id;
    }

    public void setBicycle_id(Long bicycle_id) {
        this.bicycle_id = bicycle_id;
    }

    public Long getStation_id() {
        return station_id;
    }

    public void setStation_id(Long station_id) {
        this.station_id = station_id;
    }

    public CapsulaIds(Long user_id, Long bicycle_id, Long station_id) {
        this.user_id = user_id;
        this.bicycle_id = bicycle_id;
        this.station_id = station_id;
    }





}
