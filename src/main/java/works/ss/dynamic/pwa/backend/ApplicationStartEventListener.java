package works.ss.dynamic.pwa.backend;

import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.support.Repositories;
import org.springframework.stereotype.Component;

import java.util.Iterator;

@Component
public class ApplicationStartEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private ListableBeanFactory listableBeanFactory;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        Repositories repositories = new Repositories(listableBeanFactory);


        Iterator<Class<?>> it = repositories.iterator();
        while (it.hasNext()) {
            Class<?> domainClass = it.next();
            RepositoryInformation repository = repositories.getRequiredRepositoryInformation(domainClass);
            Registry.get().addEntityToMap(domainClass, repository.getRepositoryInterface());

        }
    }
}
