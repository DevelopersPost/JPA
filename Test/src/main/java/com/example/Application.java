package com.example;

import com.example.model.Message;
import com.example.model.Message_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import javax.persistence.metamodel.SingularAttribute;
import java.util.Iterator;
import java.util.Set;

@SpringBootApplication
public class Application {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner dbOperation() {
        return x -> {
            Metamodel metamodel = entityManagerFactory.getMetamodel();
            Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();

            for (ManagedType<?> managedType : managedTypes) {
                System.out.println("Entity: " + managedType.getJavaType().getName());

                for (Attribute<?, ?> attribute : managedType.getAttributes()) {
                    System.out.println("  Attribute: " + attribute.getName() + " (Type: " + attribute.getJavaType().getName() + ")");
                }
            }

            Iterator<ManagedType<?>> iterator = managedTypes.iterator();
            ManagedType<?> managedType = iterator.next();

            if(!managedType.getJavaType().getName().equals("com.example.model.Message")){
                managedType = iterator.next();
            }

            SingularAttribute<?, ?> idAttribute = managedType.getSingularAttribute("id");
            SingularAttribute<?, ?> textAttribute = managedType.getSingularAttribute("text");

            System.out.println(idAttribute.getType().getJavaType().getName());
            System.out.println(textAttribute.getType().getJavaType().getName());

            EntityManager entityManager = entityManagerFactory.createEntityManager();

            Message message = new Message();
            message.setText("Hello");

            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();

            CriteriaBuilder cb = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<Message> query = cb.createQuery(Message.class);
            Root<Message> messageRoot = query.from(Message.class);
            query.select(messageRoot).where(cb.equal(messageRoot.get("text"), "Hello"));

            CriteriaBuilder cb1 = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<Message> query1 = cb1.createQuery(Message.class);
            Root<Message> message1 = query1.from(Message.class);
            query1.select(message1).where(cb1.equal(message1.get(Message_.text), "Hello"));

        };
    }
}
