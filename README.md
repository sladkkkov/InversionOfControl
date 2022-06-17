<h1 class="code-line" data-line-start=0 data-line-end=1 ><a id="__0"></a>Сладков Данила</h1>
<h1 class="code-line" data-line-start=1 data-line-end=2 ><a id="_1"></a>Задание</h1>
<h2 class="code-line" data-line-start=2 data-line-end=3 ><a id="_1_Configure_context_by_XML_2"></a>Шаг 1. Configure context by XML.</h2>
<p class="has-line-data" data-line-start="3" data-line-end="4">В файле context-config.xml опишем наши бины, используя property</p>
<pre><code class="has-line-data" data-line-start="5" data-line-end="15">    &lt;bean id=&quot;orderRepositoryImpl&quot;
          class=&quot;ru.neoflex.repository.impl.OrderRepositoryImpl&quot;&gt;
    &lt;/bean&gt;

    &lt;bean id=&quot;orderService&quot;
          class=&quot;ru.neoflex.service.impl.OrderServiceImpl&quot;&gt;
        &lt;property name=&quot;orderRepository&quot; ref=&quot;orderRepositoryImpl&quot;/&gt;
        &lt;property name=&quot;standardSum&quot; value=&quot;113&quot;/&gt;
    &lt;/bean&gt;
</code></pre>
<p class="has-line-data" data-line-start="15" data-line-end="16">Запускаем приложения и убеждаемся, что всё работает.</p>
<pre><code class="has-line-data" data-line-start="17" data-line-end="24">июн. 17, 2022 8:01:24 PM ru.neoflex.repository.impl.OrderRepositoryImpl save
INFO: Order Order(id=null, sum=113.0, orderDate=2022-06-17T20:01:24.858059600, payed=false, customerId=5) saved
июн. 17, 2022 8:01:24 PM ru.neoflex.service.impl.OrderServiceImpl createNew
INFO: created new order: Order(id=null, sum=113.0, orderDate=2022-06-17T20:01:24.858059600, payed=false, customerId=5)

Process finished with exit code 0
</code></pre>
<h2 class="code-line" data-line-start=25 data-line-end=26 ><a id="_2_Configure_context_by_XML_25"></a>Шаг 2. Configure context by XML.</h2>
<p class="has-line-data" data-line-start="26" data-line-end="27">В файле context-config.xml опишем наши бины, используя factory</p>
<pre><code class="has-line-data" data-line-start="28" data-line-end="38"> &lt;bean id=&quot;orderRepository&quot; class=&quot;ru.neoflex.service.ServiceFactory&quot;
          factory-method=&quot;createOrderRepository&quot;&gt;
    &lt;/bean&gt;

    &lt;bean id=&quot;orderService&quot; class=&quot;ru.neoflex.service.ServiceFactory&quot;
          factory-method=&quot;createOrderService&quot;&gt;
        &lt;constructor-arg  index=&quot;0&quot; value=&quot;555&quot;/&gt;
    &lt;/bean&gt;

</code></pre>
<p class="has-line-data" data-line-start="38" data-line-end="39">Класс ServiceFactory</p>
<pre><code class="has-line-data" data-line-start="40" data-line-end="59">package ru.neoflex.service;

import ru.neoflex.repository.impl.OrderRepositoryImpl;
import ru.neoflex.service.impl.OrderServiceImpl;


public class ServiceFactory {
    public static OrderRepositoryImpl createOrderRepository() {
        return new OrderRepositoryImpl();
    }
    
    public static OrderServiceImpl createOrderService(Double value) {
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.setOrderRepository(ServiceFactory.createOrderRepository());
        orderService.setStandardSum(value);
        return orderService;
    }
}
</code></pre>
<p class="has-line-data" data-line-start="59" data-line-end="60">Запускаем приложения и убеждаемся, что всё работает.</p>
<pre><code class="has-line-data" data-line-start="61" data-line-end="66">июн. 17, 2022 10:52:11 PM ru.neoflex.repository.impl.OrderRepositoryImpl save
INFO: Order Order(id=null, sum=555.0, orderDate=2022-06-17T22:52:11.663268300, payed=false, customerId=5) saved
июн. 17, 2022 10:52:11 PM ru.neoflex.service.impl.OrderServiceImpl createNew
INFO: created new order: Order(id=null, sum=555.0, orderDate=2022-06-17T22:52:11.663268300, payed=false, customerId=5)
</code></pre>
<h2 class="code-line" data-line-start=66 data-line-end=67 ><a id="_3_Configure_autowiring_by_XML_66"></a>Шаг 3 Configure autowiring by XML</h2>
<p class="has-line-data" data-line-start="67" data-line-end="68">Добавляем код</p>
<pre><code class="has-line-data" data-line-start="69" data-line-end="80">    &lt;context:annotation-config/&gt;
        &lt;bean id=&quot;orderRepositoryImpl&quot;
              class=&quot;ru.neoflex.repository.impl.OrderRepositoryImpl&quot;&gt;
        &lt;/bean&gt;

        &lt;bean id=&quot;orderService&quot;
              class=&quot;ru.neoflex.service.impl.OrderServiceImpl&quot;&gt;
            &lt;property name=&quot;standardSum&quot; value=&quot;115&quot;/&gt;
        &lt;/bean&gt;

</code></pre>
<p class="has-line-data" data-line-start="80" data-line-end="81">Также добавляем анотацию Autowired над полем</p>
<pre><code class="has-line-data" data-line-start="82" data-line-end="85">@Autowired
    private  OrderRepository orderRepository;
</code></pre>
<p class="has-line-data" data-line-start="85" data-line-end="86">Смотрим результат</p>
<pre><code class="has-line-data" data-line-start="87" data-line-end="94">июн. 17, 2022 11:20:21 PM ru.neoflex.repository.impl.OrderRepositoryImpl save
INFO: Order Order(id=null, sum=113.0, orderDate=2022-06-17T23:20:21.907730900, payed=false, customerId=5) saved
июн. 17, 2022 11:20:21 PM ru.neoflex.service.impl.OrderServiceImpl createNew
INFO: created new order: Order(id=null, sum=113.0, orderDate=2022-06-17T23:20:21.907730900, payed=false, customerId=5)

Process finished with exit code 0
</code></pre>
<h2 class="code-line" data-line-start=94 data-line-end=95 ><a id="_4_Configure_annotations_and_enable_them_by_XML_94"></a>Шаг 4. Configure annotations and enable them by XML</h2>
<p class="has-line-data" data-line-start="95" data-line-end="96">Создаем файл Config</p>
<pre><code class="has-line-data" data-line-start="97" data-line-end="119">package ru.neoflex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.neoflex.repository.impl.OrderRepositoryImpl;
import ru.neoflex.service.impl.OrderServiceImpl;

@Configuration
@ComponentScan
public class Config {
    @Bean
    public OrderServiceImpl orderService(){
        return new OrderServiceImpl(orderRepository());
    }

    @Bean
    public OrderRepositoryImpl orderRepository(){
        return new OrderRepositoryImpl();
    }
}
</code></pre>
<p class="has-line-data" data-line-start="119" data-line-end="120">Изменяем на AnnotationConfigApplicationContext</p>
<pre><code class="has-line-data" data-line-start="121" data-line-end="125"> AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        OrderService orderService = context.getBean(OrderService.class);
        orderService.createNew(5L);
</code></pre>
<p class="has-line-data" data-line-start="126" data-line-end="127">В context-config.xml</p>
<pre><code class="has-line-data" data-line-start="128" data-line-end="131">    &lt;context:annotation-config/&gt;
    &lt;context:component-scan base-package=&quot;ru.neoflex&quot;/&gt;
</code></pre>
<p class="has-line-data" data-line-start="131" data-line-end="133">И расставляем над классами Репозитория и Сервиса аннотацию @Component<br>
Получаем результат</p>
<pre><code class="has-line-data" data-line-start="135" data-line-end="140">июн. 17, 2022 11:56:43 PM ru.neoflex.repository.impl.OrderRepositoryImpl save
INFO: Order Order(id=null, sum=5.0, orderDate=2022-06-17T23:56:43.276970700, payed=false, customerId=5) saved
июн. 17, 2022 11:56:43 PM ru.neoflex.service.impl.OrderServiceImpl createNew
INFO: created new order: Order(id=null, sum=5.0, orderDate=2022-06-17T23:56:43.276970700, payed=false, customerId=5)
</code></pre>
