package com.example.domain.repositories

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class ClassRepository : PanacheRepositoryBase<com.example.domain.model.Class, Long> {
}
