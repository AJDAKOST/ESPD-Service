package eu.europa.ec.grow.espd.xml.base
/**
 * Created by ratoico on 12/15/15 at 3:53 PM.
 */
class AbstractExclusionCriteriaFixture extends AbstractCriteriaFixture {

    protected static void checkSelfCleaningRequirementGroup(def selfCleaningGroup) {
        assert selfCleaningGroup.ID.text() == "41dd2e9b-1bfd-44c7-93ee-56bd74a4334b"
        selfCleaningGroup.Requirement.size() == 2

        def r0 = selfCleaningGroup.Requirement[0]
        checkRequirement(r0, "20c5361b-7599-4ee6-b030-7f8323174d1e", "Have you taken measures to demonstrate your reliability (\"Self-Cleaning\")?", "INDICATOR")
        r0.ID.@schemeID.text() == "CriterionRelatedIDs"
        r0.ID.@schemeVersionID.text() == "1.0"


        def r1 = selfCleaningGroup.Requirement[1]
        checkRequirement(r1, "7b07904f-e080-401a-a3a1-9a3efeeda54b", "Please describe them", "DESCRIPTION")
        r1.ID.@schemeID.text() == "CriterionRelatedIDs"
        r1.ID.@schemeVersionID.text() == "1.0"
    }

    protected static void checkInfoAvailableElectronicallyRequirementGroup(def infoElectronicallyRequirementGroup) {
        assert infoElectronicallyRequirementGroup.ID.text() == "7458d42a-e581-4640-9283-34ceb3ad4345"
        assert infoElectronicallyRequirementGroup.RequirementGroup.size() == 0
        assert infoElectronicallyRequirementGroup.Requirement.size() == 3

        def r0 = infoElectronicallyRequirementGroup.Requirement[0]
        checkRequirement(r0, "c1347b74-1872-4060-a6db-f4044edcd7c4", "Is this information available electronically?", "INDICATOR")
        assert r0.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r0.ID.@schemeVersionID.text() == "1.0"

        def r1 = infoElectronicallyRequirementGroup.Requirement[1]
        checkRequirement(r1, "f4313bb6-21b6-499e-bdff-debe10e11d2c", "URL", "URL")
        assert r1.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r1.ID.@schemeVersionID.text() == "1.0"

        def r2 = infoElectronicallyRequirementGroup.Requirement[2]
        checkRequirement(r2, "1f1cd18e-3e01-4ca2-af4c-e2981924ba8d", "Code", "CODE")
        assert r2.ID.@schemeID.text() == "CriterionRelatedIDs"
        assert r2.ID.@schemeVersionID.text() == "1.0"
    }

}