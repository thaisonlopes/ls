package br.com.lopessolutions.base.conversor;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "converterData")
public class ConverterDatasGeral implements Converter<Object> {

	SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		Date data = null;

		if (arg2 != null) {

			try {

				df.setLenient(false);
				data = df.parse(arg2);

			} catch (Exception e) {

			}
		}

		return data;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		String d = "";

		try {

			if (arg2 != null) {

				df.setLenient(false);
				d = df.format((Date) arg2);

			}

		} catch (Exception e) {

		}

		return d;
	}

}
