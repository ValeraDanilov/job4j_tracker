package ru.job4j.tracker.hbn;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.model.Store;

import java.util.List;
import java.util.function.Function;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public <T> T add(Item item) {
        return (T) this.sf(session -> session.save(item));
    }

    @Override
    public boolean replace(int id, Item item) {
        return this.sf(
                session -> session.createQuery("update Item s set s.name = :newName, s.description = :newDescription, s.created = :newCreated where s.id = :Id")
                        .setParameter("Id", id)
                        .setParameter("newName", item.getName())
                        .setParameter("newDescription", item.getDescription())
                        .setParameter("newCreated", item.getCreated()).executeUpdate() > 0
        );
    }

    @Override
    public boolean delete(int id) {
        return this.sf(
                session -> session.createQuery("delete from Item s where s.id = :Id")
                        .setParameter("Id", id)
                        .executeUpdate() > 0
        );
    }

    @Override
    public List<Item> findAll() {
        return this.sf(
                session -> session.createQuery("from Item ").list()
        );
    }

    @Override
    public List<Item> findByName(String key) {
        return this.sf(
                session -> session.createQuery("from Item s where s.name = :name1")
                        .setParameter("name1", key).list()
        );
    }

    @Override
    public Item findById(int id) {
        return (Item) this.sf(
                session -> session.createQuery("from Item s where s.id = :Id")
                        .setParameter("Id", id).getResultList().stream().findFirst().orElse(null)
        );
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }

    private <T> T sf(Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (Exception eo) {
            session.getTransaction().rollback();
            throw eo;
        } finally {
            session.close();
        }
    }
}
