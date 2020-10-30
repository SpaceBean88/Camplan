package com.acaroom.camplan.data

import java.sql.Timestamp

data class AddCampingData(var campTitle: String?,
                          var campStartDate: Timestamp,
                          var campEndDate: Timestamp,
                          var campSiteName: String,
                          var campDevision: String,
                          var campBudget: Int?) {
}