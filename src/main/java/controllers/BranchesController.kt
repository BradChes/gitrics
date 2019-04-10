package controllers

import models.Branches
import models.BranchesLifetime
import org.springframework.web.bind.annotation.*
import services.GitService
import services.JGitService
import utils.ConfigReader

@RestController
class BranchesController(configPath: String) {

    private val jGitService: GitService

    init {
        val reader = ConfigReader(configPath)
        val options = reader.parsedConfigToOptions()
        val account = reader.parsedConfigToAccount()
        jGitService = JGitService(options, account)
    }

    @RequestMapping("{id}/branches")
    fun getBranches(@PathVariable id: Int): Branches {
        return jGitService.createBranchesObject(id, BranchType.ALL)
    }

    @RequestMapping("/branches/feat")
    fun getFeatBranches(): Branches {
        return jGitService.createBranchesObject(0, BranchType.FEAT)
    }

    @RequestMapping("/branches/spike")
    fun getSpikeBranches(): Branches {
        return jGitService.createBranchesObject(0,BranchType.SPIKE)
    }

    @RequestMapping("/branches/fix")
    fun getFixBranches(): Branches {
        return jGitService.createBranchesObject(0,BranchType.FIX)
    }

    @RequestMapping("/branches/other")
    fun getOtherBranches(): Branches {
        return jGitService.createBranchesObject(0,BranchType.OTHER)
    }

    @RequestMapping("/branches/unmerged")
    fun getUnmergedBranches(): Branches {
        return jGitService.createBranchesObject(0,BranchType.UNMERGED)
    }

    @RequestMapping("/branches/merged")
    fun getMergedBranches(): Branches {
        return jGitService.createBranchesObject(0,BranchType.MERGED)
    }

    @RequestMapping("/branches/stale")
    fun getStaleBranches(): Branches {
        return jGitService.createBranchesObject(0,BranchType.STALE)
    }

    @RequestMapping("/branches/lifetime")
    fun getAverageLifetime(): BranchesLifetime {
        return jGitService.createLifetimeObject()
    }
}

enum class BranchType {
    ALL,
    FEAT,
    SPIKE,
    FIX,
    OTHER,
    UNMERGED,
    MERGED,
    STALE
}