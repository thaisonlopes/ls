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
package org.primefaces.layout.enums;

public enum InlineMenuType {

    TOP("top"),
    BOTTOM("bottom"),
    BOTH("both");

    private String toString;

    InlineMenuType() {
    }

    InlineMenuType(String toString) {
        this.toString = toString;
    }

    @Override
    public String toString() {
        return ((this.toString != null) ? this.toString : super.toString());
    }

    public static InlineMenuType toType(String type) {
        switch(type.toLowerCase()) {
            case "top":
                return InlineMenuType.TOP;
            case "bottom":
                return InlineMenuType.BOTTOM;
            case "both":
                return InlineMenuType.BOTH;
        }

        return null;
    }
}
