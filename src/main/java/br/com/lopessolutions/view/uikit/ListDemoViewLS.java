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
package br.com.lopessolutions.view.uikit;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.model.DualListModel;
import org.primefaces.demo.domain.Product;
import org.primefaces.demo.service.ProductService;

@Named
@RequestScoped
public class ListDemoViewLS {

    private DualListModel<String> cities1;

    private List<String> cities2;

    private List<Product> products;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        List<String> citiesSource = new ArrayList<String>();
        List<String> citiesTarget = new ArrayList<String>();

        citiesSource.add("San Francisco");
        citiesSource.add("London");
        citiesSource.add("Paris");
        citiesSource.add("Istanbul");
        citiesSource.add("Berlin");
        citiesSource.add("Barcelona");
        citiesSource.add("Rome");

        cities1 = new DualListModel<String>(citiesSource, citiesTarget);

        cities2 = new ArrayList<String>();
        cities2.add("San Francisco");
        cities2.add("London");
        cities2.add("Paris");
        cities2.add("Istanbul");
        cities2.add("Berlin");
        cities2.add("Barcelona");
        cities2.add("Rome");

        this.products = this.service.getProducts();
    }

    public DualListModel<String> getCities1() {
        return cities1;
    }

    public List<String> getCities2() {
        return cities2;
    }

    public List<Product> getProducts() {
        return products;
    }

}
