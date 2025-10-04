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
package br.com.lopessolutions.domain;

import java.io.Serializable;
import java.time.LocalDate;

public class CustomerLS implements Serializable {

    private int id;
    private String name;
    private CountryLS country;
    private LocalDate date;
    private CustomerStatusLS status;
    private int activity;
    private RepresentativeLS representative;

    public CustomerLS() {}

    public CustomerLS(int id, String name, CountryLS country, LocalDate date, CustomerStatusLS status, int activity, RepresentativeLS representative) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.date = date;
        this.status = status;
        this.activity = activity;
        this.representative = representative;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryLS getCountry() {
        return country;
    }

    public void setCountry(CountryLS country) {
        this.country = country;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public CustomerStatusLS getStatus() {
        return status;
    }

    public void setStatus(CustomerStatusLS status) {
        this.status = status;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public RepresentativeLS getRepresentative() {
        return representative;
    }

    public void setRepresentative(RepresentativeLS representative) {
        this.representative = representative;
    }
}
