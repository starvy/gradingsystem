package cz.ssps.domain.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassRepository : PanacheRepositoryBase<cz.ssps.domain.model.Class, Long> {
}
