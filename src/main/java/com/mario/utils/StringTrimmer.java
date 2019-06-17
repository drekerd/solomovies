package com.mario.utils;

import lombok.Data;

import javax.annotation.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Data
@ManagedBean
@FacesConverter(forClass = String.class)
public class StringTrimmer implements Converter{


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
        return value != null ? value.trim() : null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
        return value.toString();
    }
}
