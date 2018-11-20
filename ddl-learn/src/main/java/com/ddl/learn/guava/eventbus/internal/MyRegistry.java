package com.ddl.learn.guava.eventbus.internal;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * <pre>
 * topic1->subscriber-subscribe方法
 *       ->subscriber-subscribe方法
 *       ->subscriber-subscribe方法
 * topic2->subscriber-subscribe方法
 *       ->subscriber-subscribe方法
 *       ->subscriber-subscribe方法
 * 包可见
 * </pre>
 */
class MyRegistry {

    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<MySubscriber>> subscriberContainer
            = new ConcurrentHashMap<>();

    public void bind(Object subscriber) {
        List<Method> subscribeMethods = getSubscribeMethods(subscriber);
        subscribeMethods.forEach(
                m -> tierSubscriber(subscriber, m)
        );
    }


    public void unbind(Object subscriber) {

        subscriberContainer.forEach((key, queue) ->
                queue.forEach(s ->
                {
                    if (s.getSubscribeObject() == subscriber) {
                        s.setDisable(true);
                    }
                }));
    }

    public ConcurrentLinkedQueue<MySubscriber> scanSubscriber(final String topic) {
        return subscriberContainer.get(topic);
    }

    private void tierSubscriber(Object subscriber, Method method) {
        final MySubscribe mySubscribe = method.getDeclaredAnnotation(MySubscribe.class);
        String topic = mySubscribe.topic();
        subscriberContainer.computeIfAbsent(
                topic, key -> new ConcurrentLinkedQueue<>()
        );
        subscriberContainer.get(topic).add(new MySubscriber(subscriber, method));

    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        final List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null) {
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    .filter(
                            m -> m.isAnnotationPresent(MySubscribe.class) &&
                                    m.getParameterCount() == 1 &&
                                    m.getModifiers() == Modifier.PUBLIC
                    )
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }
}
