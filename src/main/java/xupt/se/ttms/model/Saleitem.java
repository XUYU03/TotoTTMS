package xupt.se.ttms.model;

import java.math.BigDecimal;

public class Saleitem {
    private Integer saleItemId;

    private Integer ticketId;

    private Integer saleId;

    @Override
    public String toString() {
        return "Saleitem{" +
                "saleItemId=" + saleItemId +
                ", ticketId=" + ticketId +
                ", saleId=" + saleId +
                ", saleItemPrice=" + saleItemPrice +
                '}';
    }

    public Integer getSaleItemId() {
        return saleItemId;
    }

    public void setSaleItemId(Integer saleItemId) {
        this.saleItemId = saleItemId;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getSaleItemPrice() {
        return saleItemPrice;
    }

    public void setSaleItemPrice(BigDecimal saleItemPrice) {
        this.saleItemPrice = saleItemPrice;
    }

    public Saleitem() {
    }

    public Saleitem(Integer saleItemId, Integer ticketId, Integer saleId, BigDecimal saleItemPrice) {
        this.saleItemId = saleItemId;
        this.ticketId = ticketId;
        this.saleId = saleId;
        this.saleItemPrice = saleItemPrice;
    }

    private BigDecimal saleItemPrice;

}
