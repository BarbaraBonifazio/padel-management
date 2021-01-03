package it.solvingteam.padelmanagement.model.slot;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Slot {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Integer hour;
	private Integer minute;
	
	@Enumerated(EnumType.STRING)
	private SlotName slotName;
	
	public Slot() {
		
	}
	
	public Slot(Long id, Integer hour, Integer minute, SlotName slotName) {
		this.id=id;
		this.hour=hour;
		this.minute=minute;
		this.slotName=slotName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}
	
	public SlotName getSlotName() {
		return slotName;
	}

	public void setSlotName(SlotName slotName) {
		this.slotName = slotName;
	}
	
	public static SlotName convertHoursAndMinutesToSlot (Integer hour,Integer minute) {
		switch (hour) {
			case 8: 
				if(minute==0) {
					return SlotName.OTTO;
				} else if(minute==30) {
					return SlotName.OTTO_E_TRENTA;
				} else {
					return null;
				}
				
			case 9:
				if(minute==0) {
					return SlotName.NOVE;
				} else if(minute==30) {
					return SlotName.NOVE_E_TRENTA;
				} else {
					return null;
				}
				
			case 10:
				if(minute==0) {
					return SlotName.DIECI;
				} else if(minute==30) {
					return SlotName.DIECI_E_TRENTA;
				} else {
					return null;
				}
			
			case 11:
				if(minute==0) {
					return SlotName.UNDICI;
				} else if(minute==30) {
					return SlotName.UNDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 12:
				if(minute==0) {
					return SlotName.DODICI;
				} else if(minute==30) {
					return SlotName.DODICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 13:
				if(minute==0) {
					return SlotName.TREDICI;
				} else if(minute==30) {
					return SlotName.TREDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 14:
				if(minute==0) {
					return SlotName.QUATTORDICI;
				} else if(minute==30) {
					return SlotName.QUATTORDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 15:
				if(minute==0) {
					return SlotName.QUINDICI;
				} else if(minute==30) {
					return SlotName.QUINDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 16:
				if(minute==0) {
					return SlotName.SEDICI;
				} else if(minute==30) {
					return SlotName.SEDICI_E_TRENTA;
				} else {
					return null;
				}
				
			case 17:
				if(minute==0) {
					return SlotName.DICIASSETTE;
				} else if(minute==30) {
					return SlotName.DICIASSETTE_E_TRENTA;
				} else {
					return null;
				}
				
			case 18:
				if(minute==0) {
					return SlotName.DICIOTTO;
				} else if(minute==30) {
					return SlotName.DICIOTTO_E_TRENTA;
				} else {
					return null;
				}
				
			case 19:
				if(minute==0) {
					return SlotName.DICIANNOVE;
				} else if(minute==30) {
					return SlotName.DICIANNOVE_E_TRENTA;
				} else {
					return null;
				}
				
			case 20:
				if(minute==0) {
					return SlotName.VENTI;
				} else if(minute==30) {
					return SlotName.VENTI_E_TRENTA;
				} else {
					return null;
				}
				
			case 21:
				if(minute==0) {
					return SlotName.VENTUNO;
				} else if(minute==30) {
					return SlotName.VENTUNO_E_TRENTA;
				} else {
					return null;
				}
				
			case 22:
				if(minute==0) {
					return SlotName.VENTIDUE;
				} else {
					return null;
				}
			
			default: 
				return null;
		} 
	}
	
	@Override
	public String toString() {
		return " " + hour +":"+minute;
	}
}
