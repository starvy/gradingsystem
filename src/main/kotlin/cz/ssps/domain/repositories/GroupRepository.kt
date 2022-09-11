package cz.ssps.domain.repositories

import cz.ssps.domain.model.Group
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GroupRepository : PanacheRepositoryBase<Group, Long> {
}
