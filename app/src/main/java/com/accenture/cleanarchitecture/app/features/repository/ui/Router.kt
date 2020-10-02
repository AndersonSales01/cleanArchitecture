package com.accenture.cleanarchitecture.app.features.repository.ui

import com.accenture.cleanarchitecture.domain.entities.Repository

interface Router {
  fun toGoPullRequestScreen(repository: Repository)
}