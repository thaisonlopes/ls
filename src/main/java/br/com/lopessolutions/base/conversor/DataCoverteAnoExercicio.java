package br.com.lopessolutions.base.conversor;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.lopessolutions.base.util.DataUtil;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;
import jakarta.servlet.http.HttpSession;


@FacesConverter(value = "converteDataAnoEx")
public class DataCoverteAnoExercicio implements Converter<Object> {

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

			if (data != null) {

				int anoExercicio = getAnoExercicioAcesso();
				int ano = DataUtil.getAno(data);

				if (anoExercicio != ano) {
					data = null;
					throw new ConverterException(
							new FacesMessage(FacesMessage.SEVERITY_WARN, "Ano " + ano + " diferente do ano exercício " + anoExercicio,
									null));
				}

			}
		}

		return data;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {

		Date data = null;
		String d = "";

		try {

			if (arg2 != null) {

				data = (Date) arg2;

				int anoExercicio = getAnoExercicioAcesso();
				int ano = DataUtil.getAno(data);

				if (anoExercicio == ano) {
					df.setLenient(false);
					d = df.format((Date) arg2);
				}

			}

		} catch (Exception e) {

		}

		return d;
	}

	// usar esta
	public Integer getAnoExercicioAcesso() {
		try {

			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			Object ob = session.getAttribute("AnoSessao");

			if (ob != null) {
				Integer ano = (Integer) ob;

				return ano;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
