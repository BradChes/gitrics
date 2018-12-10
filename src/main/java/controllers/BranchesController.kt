package controllers

import models.Account
import models.Branches
import org.springframework.web.bind.annotation.*
import services.GitService
import services.JGitService

@RestController
class BranchesController {

    private val jGitService: GitService

    init {
        val remoteRepositoryUri = Account.REMOTE_REPO_URI
        jGitService = JGitService(remoteRepositoryUri)
    }

    @RequestMapping("/branches")
    fun getBranches(): Branches {
        return jGitService.createBranchesObject(BranchType.ALL)
    }

    @RequestMapping("/branches/feat")
    fun getFeatBranches(): Branches {
        return jGitService.createBranchesObject(BranchType.FEAT)
    }

    @RequestMapping("/branches/spike")
    fun getSpikeBranches(): Branches {
        return jGitService.createBranchesObject(BranchType.SPIKE)
    }

    @RequestMapping("/branches/fix")
    fun getFixBranches(): Branches {
        return jGitService.createBranchesObject(BranchType.FIX)
    }

    @RequestMapping("/branches/other")
    fun getOtherBranches(): Branches {
        return jGitService.createBranchesObject(BranchType.OTHER)
    }

    @RequestMapping("/branches/unmerged")
    fun getUnmergedBranches(): Branches {
        return jGitService.createBranchesObject(BranchType.UNMERGED)
    }

}

enum class BranchType {
    ALL,
    FEAT,
    SPIKE,
    FIX,
    OTHER,
    UNMERGED
}