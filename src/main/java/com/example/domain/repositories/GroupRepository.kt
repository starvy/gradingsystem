package com.example.domain.repositories

import com.example.domain.model.Group
import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepositoryBase
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class GroupRepository : PanacheRepositoryBase<Group, Long>