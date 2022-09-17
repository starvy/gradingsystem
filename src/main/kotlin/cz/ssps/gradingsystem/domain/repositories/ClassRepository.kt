package cz.ssps.gradingsystem.domain.repositories

import cz.ssps.gradingsystem.domain.model.Class
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassRepository : PanacheRepositoryBase<Class, Long>
