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
package org.primefaces.demo.view.favorites;

import org.primefaces.demo.domain.Product;
import org.primefaces.demo.service.ProductService;
import software.xdev.chartjs.model.charts.BarChart;
import software.xdev.chartjs.model.charts.DoughnutChart;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.charts.PieChart;
import software.xdev.chartjs.model.color.RGBAColor;
import software.xdev.chartjs.model.data.BarData;
import software.xdev.chartjs.model.data.DoughnutData;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.data.PieData;
import software.xdev.chartjs.model.dataset.BarDataset;
import software.xdev.chartjs.model.dataset.DoughnutDataset;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.dataset.PieDataset;
import software.xdev.chartjs.model.enums.IndexAxis;
import software.xdev.chartjs.model.options.*;
import software.xdev.chartjs.model.options.elements.Fill;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianScaleOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianTickOptions;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class DashboardAnalyticsView implements Serializable {

    private List<Product> products;

    private String monthlyChartModel;
    private String donutChartModel;
    private String pieChartModel;

    private int storeADiff = 0;
    private int storeBDiff = 0;
    private int storeCDiff = 0;
    private int storeDDiff = 0;
    private int storeATotalValue = 100;
    private int storeBTotalValue = 120;
    private int storeCTotalValue = 150;
    private int storeDTotalValue = 80;

    private String storeAModel;
    private String storeBModel;
    private String storeCModel;
    private String storeDModel;

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = this.service.getProducts();

        createMonthlyChart();
        createDonutChart();
        createPieChart();

        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");

        createStoreAChart(labels);
        createStoreBChart(labels);
        createStoreCChart(labels);
        createStoreDChart(labels);
    }

    public void createMonthlyChart() {
        monthlyChartModel = new BarChart()
                .setData(new BarData()
                        .addDataset(new BarDataset()
                                .setData(65, 59, 80, 81, 56, 55, 40)
                                .setLabel("My First Dataset")
                                .setBackgroundColor(new RGBAColor(255, 99, 132, 0.2))
                                .setBorderColor(new RGBAColor(255, 99, 132))
                                .setBorderWidth(1))
                        .addDataset(new BarDataset()
                                .setData(85, 69, 20, 51, 76, 75, 10)
                                .setLabel("My Second Dataset")
                                .setBackgroundColor(new RGBAColor(255, 159, 64, 0.2))
                                .setBorderColor(new RGBAColor(255, 159, 64))
                                .setBorderWidth(1)
                        )
                        .setLabels("January", "February", "March", "April", "May", "June", "July"))
                .setOptions(new BarOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setIndexAxis(IndexAxis.X)
                        .setScales(new Scales().addScale(Scales.ScaleAxis.Y, new CartesianScaleOptions()
                                .setStacked(false)
                                .setTicks(new CartesianTickOptions()
                                        .setAutoSkip(true)
                                        .setMirror(true)))
                        )
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Bar Chart using XDEV java model")))
                ).toJson();
    }

    public void createDonutChart() {
        donutChartModel = new DoughnutChart()
                .setData(new DoughnutData()
                        .addDataset(new DoughnutDataset()
                                .setData(BigDecimal.valueOf(300),
                                        BigDecimal.valueOf(50),
                                        BigDecimal.valueOf(100))
                                .addBackgroundColors(
                                        new RGBAColor(255, 99, 132),
                                        new RGBAColor(54, 162, 235),
                                        new RGBAColor(255, 205, 86))
                        )
                        .setLabels("Red", "Blue", "Yellow"))
                .setOptions(new DoughnutOptions().setMaintainAspectRatio(Boolean.FALSE))
                .toJson();
    }

    public void createPieChart() {
        pieChartModel = new PieChart()
                .setData(new PieData()
                        .addDataset(new PieDataset()
                                .setData(BigDecimal.valueOf(300), BigDecimal.valueOf(50), BigDecimal.valueOf(100))
                                .setLabel("My First Dataset")
                                .addBackgroundColors(new RGBAColor(255, 99, 132), new RGBAColor(54, 162, 235), new RGBAColor(255, 205, 86))
                        )
                        .setLabels("Red", "Blue", "Yellow"))
                .toJson();
    }

    public String createStoreChart(List<String> labels, List<Number> values) {
        return new LineChart()
                .setData(new LineData()
                        .addDataset(new LineDataset()
                                .setData(values)
                                .setLabel("My First Dataset")
                                .setBorderColor(new RGBAColor(75, 192, 192))
                                .setLineTension(0.1f)
                                .setFill(new Fill<Boolean>(false)))
                        .setLabels(labels))
                .setOptions(new LineOptions()
                        .setResponsive(true)
                        .setMaintainAspectRatio(false)
                        .setPlugins(new Plugins()
                                .setTitle(new Title()
                                        .setDisplay(true)
                                        .setText("Line Chart Subtitle")))
                ).toJson();
    }

    public void createStoreAChart(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(55);
        values.add(3);
        values.add(45);
        values.add(6);
        values.add(44);
        values.add(58);
        values.add(84);
        values.add(68);
        values.add(64);

        storeAModel = createStoreChart(labels, values);
    }

    public void createStoreBChart(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(81);
        values.add(75);
        values.add(63);
        values.add(100);
        values.add(69);
        values.add(79);
        values.add(38);
        values.add(37);
        values.add(76);

        storeBModel = createStoreChart(labels, values);
    }

    public void createStoreCChart(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(99);
        values.add(55);
        values.add(22);
        values.add(72);
        values.add(24);
        values.add(79);
        values.add(35);
        values.add(91);
        values.add(48);

        storeCModel = createStoreChart(labels, values);
    }

    public void createStoreDChart(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(5);
        values.add(51);
        values.add(68);
        values.add(82);
        values.add(28);
        values.add(21);
        values.add(29);
        values.add(45);
        values.add(44);

        storeDModel = createStoreChart(labels, values);
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getMonthlyChartModel() {
        return monthlyChartModel;
    }

    public String getDonutChartModel() {
        return donutChartModel;
    }

    public String getPieChartModel() {
        return pieChartModel;
    }

    public int getStoreADiff() {
        return storeADiff;
    }

    public void setStoreADiff(int storeADiff) {
        this.storeADiff = storeADiff;
    }

    public int getStoreBDiff() {
        return storeBDiff;
    }

    public void setStoreBDiff(int storeBDiff) {
        this.storeBDiff = storeBDiff;
    }

    public int getStoreCDiff() {
        return storeCDiff;
    }

    public void setStoreCDiff(int storeCDiff) {
        this.storeCDiff = storeCDiff;
    }

    public int getStoreDDiff() {
        return storeDDiff;
    }

    public void setStoreDDiff(int storeDDiff) {
        this.storeDDiff = storeDDiff;
    }

    public int getStoreATotalValue() {
        return storeATotalValue;
    }

    public void setStoreATotalValue(int storeATotalValue) {
        this.storeATotalValue = storeATotalValue;
    }

    public int getStoreBTotalValue() {
        return storeBTotalValue;
    }

    public void setStoreBTotalValue(int storeBTotalValue) {
        this.storeBTotalValue = storeBTotalValue;
    }

    public int getStoreCTotalValue() {
        return storeCTotalValue;
    }

    public void setStoreCTotalValue(int storeCTotalValue) {
        this.storeCTotalValue = storeCTotalValue;
    }

    public int getStoreDTotalValue() {
        return storeDTotalValue;
    }

    public void setStoreDTotalValue(int storeDTotalValue) {
        this.storeDTotalValue = storeDTotalValue;
    }

    public String getStoreAModel() {
        return storeAModel;
    }

    public String getStoreBModel() {
        return storeBModel;
    }

    public String getStoreCModel() {
        return storeCModel;
    }

    public String getStoreDModel() {
        return storeDModel;
    }
}
