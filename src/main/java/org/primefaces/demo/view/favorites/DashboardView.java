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

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.demo.domain.Product;
import org.primefaces.demo.service.ProductService;
import software.xdev.chartjs.model.charts.LineChart;
import software.xdev.chartjs.model.color.RGBAColor;
import software.xdev.chartjs.model.data.LineData;
import software.xdev.chartjs.model.dataset.LineDataset;
import software.xdev.chartjs.model.options.LegendOptions;
import software.xdev.chartjs.model.options.LineOptions;
import software.xdev.chartjs.model.options.Plugins;
import software.xdev.chartjs.model.options.Title;
import software.xdev.chartjs.model.options.elements.Fill;
import software.xdev.chartjs.model.options.scale.GridLineConfiguration;
import software.xdev.chartjs.model.options.scale.Scales;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianScaleOptions;
import software.xdev.chartjs.model.options.scale.cartesian.CartesianTickOptions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Named
@ViewScoped
public class DashboardView implements Serializable {

    private List<Product> products;
    private List<Message> chatMessages;
    private String[] chatEmojis;
    private List<Event> chronolineEvents;

    private String ordersChartModel;
    private String overviewChartModel1;
    private String overviewChartModel2;
    private String overviewChartModel3;
    private String overviewChartModel4;

    private String chatMessage = "";

    @Inject
    private ProductService service;

    @PostConstruct
    public void init() {
        products = this.service.getProducts();

        chatMessages = new ArrayList<>();
        chatMessages.add(new Message("Ioni Bowcher", "images/avatar/ionibowcher.png", new ArrayList<String>(Arrays.asList("Hey M. hope you are well.", "Our idea is accepted by the board. Now it's time to execute it"))));
        chatMessages.add(new Message(new ArrayList<String>(Arrays.asList("We did it! ðŸ¤ "))));
        chatMessages.add(new Message("Ioni Bowcher", "images/avatar/ionibowcher.png", new ArrayList<String>(Arrays.asList("That's really good!"))));
        chatMessages.add(new Message(Arrays.asList("But it's important to ship MVP ASAP")));
        chatMessages.add(new Message("Ioni Bowcher", "images/avatar/ionibowcher.png",new ArrayList<String>(Arrays.asList("I'll be looking at the process then, just to be sure ðŸ¤“"))));
        chatMessages.add(new Message(new ArrayList<String>(Arrays.asList("That's awesome. Thanks!"))));

        chatEmojis = new String[]{
            "ðŸ˜€", "ðŸ˜ƒ", "ðŸ˜„", "ðŸ˜", "ðŸ˜†", "ðŸ˜…", "ðŸ˜‚", "ðŸ¤£", "ðŸ˜‡", "ðŸ˜‰", "ðŸ˜Š", "ðŸ™‚", "ðŸ™ƒ", "ðŸ˜‹", "ðŸ˜Œ", "ðŸ˜", "ðŸ¥°", "ðŸ˜˜", "ðŸ˜—", "ðŸ˜™", "ðŸ˜š", "ðŸ¤ª", "ðŸ˜œ", "ðŸ˜", "ðŸ˜›",
            "ðŸ¤‘", "ðŸ˜Ž", "ðŸ¤“", "ðŸ§", "ðŸ¤ ", "ðŸ¥³", "ðŸ¤—", "ðŸ¤¡", "ðŸ˜", "ðŸ˜¶", "ðŸ˜", "ðŸ˜‘", "ðŸ˜’", "ðŸ™„", "ðŸ¤¨", "ðŸ¤”", "ðŸ¤«", "ðŸ¤­", "ðŸ¤¥", "ðŸ˜³", "ðŸ˜ž", "ðŸ˜Ÿ", "ðŸ˜ ", "ðŸ˜¡", "ðŸ¤¬", "ðŸ˜”",
            "ðŸ˜Ÿ", "ðŸ˜ ", "ðŸ˜¡", "ðŸ¤¬", "ðŸ˜”", "ðŸ˜•", "ðŸ™", "ðŸ˜¬", "ðŸ¥º", "ðŸ˜£", "ðŸ˜–", "ðŸ˜«", "ðŸ˜©", "ðŸ¥±", "ðŸ˜¤", "ðŸ˜®", "ðŸ˜±", "ðŸ˜¨", "ðŸ˜°", "ðŸ˜¯", "ðŸ˜¦", "ðŸ˜§", "ðŸ˜¢", "ðŸ˜¥", "ðŸ˜ª", "ðŸ¤¤"
        };

        chronolineEvents = new ArrayList<>();
        chronolineEvents.add(new Event("Ordered", "15/10/2020 10:30", "pi pi-shopping-cart", "#E91E63", "Richard Jones (C8012) has ordered a blue t-shirt for $79."));
        chronolineEvents.add(new Event("Processing", "15/10/2020 14:00", "pi pi-cog", "#FB8C00", "Order #99207 has processed succesfully."));
        chronolineEvents.add(new Event("Shipped", "15/10/2020 16:15", "pi pi-compass", "#673AB7", "Order #99207 has shipped with shipping code 2222302090."));
        chronolineEvents.add(new Event("Delivered", "16/10/2020 10:00", "pi pi-check-square", "#0097A7", "Richard Jones (C8012) has recieved his blue t-shirt."));

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

        createOrdersChart(labels);
        createOverviewChart1(labels);
        createOverviewChart2(labels);
        createOverviewChart3(labels);
        createOverviewChart4(labels);
    }

    public void createOrdersChart(List<String> labels) {
        List<Number> newOrders = new ArrayList<>();
        newOrders.add(31);
        newOrders.add(83);
        newOrders.add(69);
        newOrders.add(29);
        newOrders.add(62);
        newOrders.add(25);
        newOrders.add(59);
        newOrders.add(26);
        newOrders.add(46);

        List<Number> completedOrders = new ArrayList<>();
        completedOrders.add(67);
        completedOrders.add(98);
        completedOrders.add(27);
        completedOrders.add(88);
        completedOrders.add(38);
        completedOrders.add(3);
        completedOrders.add(22);
        completedOrders.add(60);
        completedOrders.add(56);

        ordersChartModel = new LineChart()
                .setData(new LineData()
                        .addDataset(new LineDataset()
                                .setData(newOrders)
                                .setLabel("New Orders")
                                .setBorderColor(new RGBAColor(77, 208, 225))
                                .setBackgroundColor(new RGBAColor(77, 208, 225, 0.8))
                                .setBorderWidth(2)
                                .setLineTension(0.4f)
                                .setFill(new Fill<Boolean>(false)))
                        .addDataset(new LineDataset()
                                .setData(completedOrders)
                                .setLabel("Completed Orders")
                                .setBorderColor(new RGBAColor(63, 81, 181))
                                .setBackgroundColor(new RGBAColor(63, 81, 181, 0.8))
                                .setBorderWidth(2)
                                .setLineTension(0.4f)
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

    public String createOverviewChart(List<String> labels, List<Number> values) {
        LineOptions options = new LineOptions()
                .setResponsive(true)
                .setMaintainAspectRatio(false)
                .setPlugins(new Plugins()
                        .setLegend(new LegendOptions().
                                setDisplay(false))
                        .setTitle(new Title()
                                .setDisplay(false)));
        options.setScales(new Scales()
                .addScale(Scales.ScaleAxis.X, new CartesianScaleOptions()
                        .setTicks(new CartesianTickOptions().setDisplay(false))
                        .setGrid(new GridLineConfiguration().setDisplay(false)))
                .addScale(Scales.ScaleAxis.Y, new CartesianScaleOptions()
                        .setTicks(new CartesianTickOptions().setDisplay(false))
                        .setGrid(new GridLineConfiguration().setDisplay(false))));

        return new LineChart()
                .setData(new LineData()
                        .addDataset(new LineDataset()
                                .setData(values)
                                .setLabel("Data")
                                .setBorderColor(new RGBAColor(75, 192, 192))
                                .setFill(new Fill<Boolean>(false)))
                        .setLabels(labels))
                .setOptions(options).toJson();
    }

    public void createOverviewChart1(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(50);
        values.add(64);
        values.add(32);
        values.add(24);
        values.add(18);
        values.add(27);
        values.add(20);
        values.add(36);
        values.add(30);

        overviewChartModel1 = createOverviewChart(labels, values);
    }

    public void createOverviewChart2(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(11);
        values.add(30);
        values.add(52);
        values.add(35);
        values.add(39);
        values.add(20);
        values.add(14);
        values.add(18);
        values.add(29);

        overviewChartModel2 = createOverviewChart(labels, values);
    }

    public void createOverviewChart3(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(20);
        values.add(29);
        values.add(39);
        values.add(36);
        values.add(45);
        values.add(24);
        values.add(28);
        values.add(20);
        values.add(15);

        overviewChartModel3 = createOverviewChart(labels, values);
    }

    public void createOverviewChart4(List<String> labels) {
        List<Number> values = new ArrayList<>();
        values.add(30);
        values.add(39);
        values.add(50);
        values.add(21);
        values.add(33);
        values.add(18);
        values.add(10);
        values.add(24);
        values.add(20);

        overviewChartModel4 = createOverviewChart(labels, values);
    }

    public void addEmoji(String emoji) {
        chatMessage += emoji;
    }

    public void addMessage() {
        Message lastMessage = chatMessages.get(chatMessages.size() - 1);

        if (lastMessage.from != null) {
            chatMessages.add(new Message(new ArrayList<String>(Arrays.asList(chatMessage))));
        }
        else {
            lastMessage.messages.add(chatMessage);
        }

        Pattern pattern = Pattern.compile("primeng|primereact|primefaces|primevue", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(chatMessage);
        if (matcher.find()) {
            chatMessages.add(new Message("Ioni Bowcher", "images/avatar/ionibowcher.png", new ArrayList<String>(Arrays.asList("Always bet on Prime!"))));
        }

        chatMessage = "";
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Message> getChatMessages() {
        return chatMessages;
    }

    public String[] getChatEmojis() {
        return chatEmojis;
    }

    public List<Event> getChronolineEvents() {
        return chronolineEvents;
    }

    public String getOrdersChartModel() {
        return ordersChartModel;
    }

    public String getOverviewChartModel1() {
        return overviewChartModel1;
    }

    public String getOverviewChartModel2() {
        return overviewChartModel2;
    }

    public String getOverviewChartModel3() {
        return overviewChartModel3;
    }

    public String getOverviewChartModel4() {
        return overviewChartModel4;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public static class Event {

        private String status;
        private String date;
        private String icon;
        private String color;
        private String description;

        public Event() {
        }

        public Event(String status, String date, String icon, String color, String description) {
            this.status = status;
            this.date = date;
            this.icon = icon;
            this.color = color;
            this.description = description;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

    public static class Message {

        private String from;
        private String url;
        private List<String> messages;

        public Message() {
        }

        public Message(List<String> messages) {
            this.messages = messages;
        }

        public Message(String from, String url, List<String> messages) {
            this(messages);
            this.from = from;
            this.url = url;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<String> getMessages() {
            return messages;
        }

        public void setMessages(List<String> messages) {
            this.messages = messages;
        }
    }
}
