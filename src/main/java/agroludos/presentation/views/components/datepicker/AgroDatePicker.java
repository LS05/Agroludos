package agroludos.presentation.views.components.datepicker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import eu.schudt.javafx.controls.calendar.DatePicker;

public class AgroDatePicker {
	
	private DatePicker datePicker;

	public AgroDatePicker(){
		this.datePicker = new DatePicker(Locale.ENGLISH);
		this.datePicker.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
		this.datePicker.getCalendarView().todayButtonTextProperty().set("Today");
		this.datePicker.getCalendarView().setShowWeeks(false);
		String css = this.getClass().getResource("DatePicker.css").toExternalForm();
		this.datePicker.getStylesheets().add(css);
	}
	
	public DatePicker getDatePicker(){
		return this.datePicker;
	}
	
	public Date getSelectedDate(){
		return this.datePicker.getSelectedDate();
	}

	public void setSelectedDate(Date data){
		this.datePicker.setSelectedDate(data);
	}
}