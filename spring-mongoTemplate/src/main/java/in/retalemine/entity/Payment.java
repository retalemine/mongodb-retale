package in.retalemine.entity;

import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Field;

public class Payment {

	@Field("mode")
	private PaymentMode payMode;
	@Field("delayed")
	private Boolean isDelayedPay;
	@Field("paid")
	private Boolean isPaid;
	@Field("date")
	private Date paidDate;

	public Payment(PaymentMode payMode, Boolean isDelayedPay, Boolean isPaid,
			Date paidDate) {
		super();
		this.payMode = payMode;
		this.isDelayedPay = isDelayedPay;
		this.isPaid = isPaid;
		this.paidDate = paidDate;
	}

	public PaymentMode getPayMode() {
		return payMode;
	}

	public void setPayMode(PaymentMode payMode) {
		this.payMode = payMode;
	}

	public Boolean getIsDelayedPay() {
		return isDelayedPay;
	}

	public void setIsDelayedPay(Boolean isDelayedPay) {
		this.isDelayedPay = isDelayedPay;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
}
