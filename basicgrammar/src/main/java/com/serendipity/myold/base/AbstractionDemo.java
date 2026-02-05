package com.serendipity.myold.base;

import cn.hutool.core.net.URLEncodeUtil;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 抽象是提取共性、忽略细节的过程。
 */

@SuppressWarnings("all")
public class AbstractionDemo {

    class EntityManager<T> {
        // 模拟实体管理器
        void persist(T t) {
        }

        T find(Class<T> clazz, Object id) {
            try {
                return clazz.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Specification<T> {
        // 模拟查询条件
    }


    // 高度抽象的数据访问层
    interface Repository<T, ID> {
        Optional<T> findById(ID id);

        List<T> findAll();

        T save(T entity);

        void delete(T entity);

        boolean existsById(ID id);
    }

    // 抽象类提供通用实现
    public abstract class AbstractJpaRepository<T, ID> implements Repository<T, ID> {

        protected final EntityManager entityManager;
        protected final Class<T> entityClass;

        protected AbstractJpaRepository(EntityManager em, Class<T> entityClass) {
            this.entityManager = em;
            this.entityClass = entityClass;
        }

        @Override
        public Optional<T> findById(ID id) {
           // return Optional.ofNullable(entityManager.find(entityClass, id));
            return Optional.empty();
        }

        @Override
        public T save(T entity) {
            entityManager.persist(entity);
            return entity;
        }

        // 留给子类实现的特定查询
        protected List<T> findByCondition(Specification<T> spec) {
//            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//            CriteriaQuery<T> query = cb.createQuery(entityClass);
//            Root<T> root = query.from(entityClass);
//
//            Predicate predicate = spec.toPredicate(root, query, cb);
//            if (predicate != null) {
//                query.where(predicate);
//            }
//
//            return entityManager.createQuery(query).getResultList();
            return null;
        }
    }


}
