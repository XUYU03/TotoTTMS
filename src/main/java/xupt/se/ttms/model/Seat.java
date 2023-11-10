package xupt.se.ttms.model;

public class Seat {
    private Integer seatId;
    private Integer studioId;
    private Integer seatRow;
    private Integer seatColumn;
    private Integer seatStatus;

    public Seat() {
    }

    public Seat(Integer seatId, Integer studioId, Integer seatRow, Integer seatColumn, Integer seatStatus) {
        this.seatId = seatId;
        this.studioId = studioId;
        this.seatRow = seatRow;
        this.seatColumn = seatColumn;
        this.seatStatus = seatStatus;
    }

    /**
     * 获取
     * @return seatId
     */
    public Integer getSeatId() {
        return seatId;
    }

    /**
     * 设置
     * @param seatId
     */
    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    /**
     * 获取
     * @return studioId
     */
    public Integer getStudioId() {
        return studioId;
    }

    /**
     * 设置
     * @param studioId
     */
    public void setStudioId(Integer studioId) {
        this.studioId = studioId;
    }

    /**
     * 获取
     * @return seatRow
     */
    public Integer getSeatRow() {
        return seatRow;
    }

    /**
     * 设置
     * @param seatRow
     */
    public void setSeatRow(Integer seatRow) {
        this.seatRow = seatRow;
    }

    /**
     * 获取
     * @return seatColumn
     */
    public Integer getSeatColumn() {
        return seatColumn;
    }

    /**
     * 设置
     * @param seatColumn
     */
    public void setSeatColumn(Integer seatColumn) {
        this.seatColumn = seatColumn;
    }

    /**
     * 获取
     * @return seatStatus
     */
    public Integer getSeatStatus() {
        return seatStatus;
    }

    /**
     * 设置
     * @param seatStatus
     */
    public void setSeatStatus(Integer seatStatus) {
        this.seatStatus = seatStatus;
    }

    public String toString() {
        return "Seat{seatId = " + seatId + ", studioId = " + studioId + ", seatRow = " + seatRow + ", seatColumn = " + seatColumn + ", seatStatus = " + seatStatus + "}";
    }
}
