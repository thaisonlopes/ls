/**
 * Copyright 2009-2025 PrimeTek.
 *
 * https://www.primefaces.org/layouts/licenses
 *
 * Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * Licensed under PrimeFaces Commercial License, Version 1.0 (the "License");
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.primefaces.layout.component;

import jakarta.faces.application.ResourceDependency;
import jakarta.faces.event.ComponentSystemEventListener;

import org.primefaces.component.menu.AbstractMenu;
import org.primefaces.component.api.Widget;

@ResourceDependency(library = "primefaces", name = "components.css")
@ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
@ResourceDependency(library = "primefaces", name = "jquery/jquery-plugins.js")
@ResourceDependency(library = "primefaces", name = "core.js")
@ResourceDependency(library = "layout", name = "primeicons/primeicons.css")
@ResourceDependency(library = "layout", name = "js/layout.menu.js")
public class UltimaMenu extends AbstractMenu implements Widget, ComponentSystemEventListener {

    public static final String COMPONENT_TYPE = "org.primefaces.component.UltimaMenu";
    public static final String COMPONENT_FAMILY = "org.primefaces.component";
    private static final String DEFAULT_RENDERER = "org.primefaces.component.UltimaMenuRenderer";

    
    protected enum PropertyKeys {

        widgetVar, model, style, styleClass, stateKey, stateStorage;

        String toString;

        PropertyKeys(String toString) {
            this.toString = toString;
        }

        PropertyKeys() {
        }

        public String toString() {
            return ((this.toString != null) ? this.toString : super.toString());
        }
    }

    public UltimaMenu() {
        setRendererType(DEFAULT_RENDERER);
    }

    public String getFamily() {
        return COMPONENT_FAMILY;
    }

    public java.lang.String getWidgetVar() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(java.lang.String _widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, _widgetVar);
    }

    public org.primefaces.model.menu.MenuModel getModel() {
        return (org.primefaces.model.menu.MenuModel) getStateHelper().eval(PropertyKeys.model, null);
    }

    public void setModel(org.primefaces.model.menu.MenuModel _model) {
        getStateHelper().put(PropertyKeys.model, _model);
    }

    public java.lang.String getStyle() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.style, null);
    }

    public void setStyle(java.lang.String _style) {
        getStateHelper().put(PropertyKeys.style, _style);
    }

    public java.lang.String getStyleClass() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.styleClass, null);
    }

    public void setStyleClass(java.lang.String _styleClass) {
        getStateHelper().put(PropertyKeys.styleClass, _styleClass);
    }
    
    public java.lang.String getStateKey() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.stateKey, "pf-ultima-menu");
    }

    public void setStateKey(java.lang.String _stateKey) {
        getStateHelper().put(PropertyKeys.stateKey, _stateKey);
    }

    public java.lang.String getStateStorage() {
        return (java.lang.String) getStateHelper().eval(PropertyKeys.stateStorage, "session");
    }

    public void setStateStorage(java.lang.String _stateStorage) {
        getStateHelper().put(PropertyKeys.stateStorage, _stateStorage);
    }

}
