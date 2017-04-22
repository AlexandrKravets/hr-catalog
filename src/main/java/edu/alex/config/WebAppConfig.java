package edu.alex.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:config.properties")
@EnableJpaRepositories(basePackages = "edu.alex.model.repository")
@EnableTransactionManagement // for use JTA
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {


    @Value("${hibernate.dialect}")
    private String sqlDialect;

    @Value("${hbm2ddl.auto}")
    private String hbm2dllAuto;

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf){ //управляет транзакцией
        return new JpaTransactionManager(emf);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory
        (@SuppressWarnings("SpringJavaAutowiringInspection") DataSource dataSource, JpaVendorAdapter jpaVendeorAdapter) {
        Properties jpaProp = new Properties();
        jpaProp.put("hibernate.hbm2ddl.auto", hbm2dllAuto);

        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource);
        entityManagerFactory.setJpaVendorAdapter(jpaVendeorAdapter);
        entityManagerFactory.setPackagesToScan("edu.alex.model.repository");
        entityManagerFactory.setJpaProperties(jpaProp);

        return entityManagerFactory;
    }

    @Bean // указывает кто будет выступать JPA реализацией
    public JpaVendorAdapter jpaVendorAdapter() {  // здесь избавляемся от persistance.xml для Hibernate
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabasePlatform(sqlDialect);// указывает диалект
        return adapter;
    }

    @Bean
    public UrlBasedViewResolver setupViewResolver() {
        UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setOrder(1);
        return resolver;
    }



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.
                addResourceHandler("swagger-ui.html").
                addResourceLocations("classpath:/META-INF/resources/");
        registry.
                addResourceHandler("/webjars/**").
                addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/static/");

    }

}