package com.anderson.cleanarchitecture.app.features.repository.ui

import com.anderson.cleanarchitecture.domain.entities.Repository

interface Router {
  fun toGoPullRequestScreen(repository: Repository)
}