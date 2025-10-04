package br.com.lopessolutions.base.conversor;

import jakarta.enterprise.context.Dependent;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@Dependent
@FacesConverter(forClass = EntidadeGenericaAC.class, managed = true)
public class EntidadeGenericaACConverter implements Converter<EntidadeGenericaAC> {

	@Override
	public EntidadeGenericaAC getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		if (value != null && !value.isEmpty()) {
			return (EntidadeGenericaAC) uiComponent.getAttributes().get(value);
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, EntidadeGenericaAC value) {
		if (value != null && value.getId() != null) {
			uiComponent.getAttributes().put(value.getId().toString(), value);
			return value.getId().toString();
		}
		return "";
	}

}
