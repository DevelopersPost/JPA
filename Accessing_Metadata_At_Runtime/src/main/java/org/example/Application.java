package org.example;

import org.example.model.Message;
import org.example.model.Message_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.Metamodel;
import java.util.Set;

@SpringBootApplication
public class Application {

    @Autowired
    EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @Bean
    public CommandLineRunner metaDataTest(){
        return x -> {
            Metamodel metamodel = entityManagerFactory.getMetamodel();
            Set<ManagedType<?>> managedTypes = metamodel.getManagedTypes();

            for (ManagedType<?> managedType:managedTypes){
                System.out.println("Entity:"+managedType.getJavaType().getName());

                for (Attribute<?,?> attribute :managedType.getAttributes()){
                    System.out.println("Attribute:"+ attribute.getName() + "type: "+ attribute.getJavaType().getName());
                }
            }

            Message message = new Message();
            message.setText("Hello");

            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();

            CriteriaBuilder criteriaBuilder = entityManagerFactory.getCriteriaBuilder();
            CriteriaQuery<Message> query = criteriaBuilder.createQuery(Message.class);
            Root<Message> messageRoot = query.from(Message.class);
            //query.select(messageRoot).where(criteriaBuilder.equal(messageRoot.get("text1"),"Hello"));
            query.select(messageRoot).where(criteriaBuilder.equal(messageRoot.get(Message_.text),"Hello"));

            TypedQuery<Message> query1 = entityManager.createQuery(query);
            System.out.println(query1.getSingleResult().getText());


        };
    }


}
