package models.configs

data class ParsedConfig(val username: String,
                        val accessToken: String,
                        val repoUrls: List<String>,
                        val repoPath: String,
                        val branchMinimum: Int,
                        val branchMaximum: Int,
                        val branchesAverageLifetimeMinimumThreshold: Int,
                        val branchesAverageLifetimeMaximumThreshold: Int,
                        val staleDefinition: Int)