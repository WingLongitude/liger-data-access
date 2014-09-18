# Version 2.11.1
Released on 2014-09-18

## Change set
https://github.com/WingLongitude/liger-data-access/issues?q=milestone%3A2.11.1+is%3Aclosed

# Version 2.11.0
Released on 2014-09-16

## Database schema update
[2.10.0_to_2.11.0.sql](https://raw.githubusercontent.com/Canadensys/canadensys-data-access/dev/script/migrations/occurrence/2.10.0_to_2.11.0.sql)

## Breaking change
###sourceFileId removed from ResourceContactModel
With the addition of ResourceInformationModel, ResourceContact model is now linked to this new model and not by sourceFileId anymore. This will cause an issue with contact data that is already harvested. The longa-harvester should provide a job to (re)harvest resource information only.

## Change set
https://github.com/WingLongitude/liger-data-access/issues?q=milestone%3A2.11.0+is%3Aclosed