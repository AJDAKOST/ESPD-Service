package eu.europa.ec.grow.espd.xml.response.selection

import eu.europa.ec.grow.espd.domain.AvailableElectronically
import eu.europa.ec.grow.espd.domain.EspdDocument
import eu.europa.ec.grow.espd.domain.TechnicalProfessionalCriterion
import eu.europa.ec.grow.espd.xml.base.AbstractSelectionCriteriaFixture
/**
 * Created by ratoico on 12/9/15 at 1:48 PM.
 */
class TechniciansTechnicalBodiesResponseTest extends AbstractSelectionCriteriaFixture {

    def "16. should contain the 'Technicians or technical bodies for quality control' criterion"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then: "CriterionID element"
        request.Criterion.size() == 1
        checkCriterionId(request, idx, "3aaca389-4a7b-406b-a4b9-080845d127e7")

        then: "CriterionTypeCode element"
        checkCriterionTypeCode(request, idx, "SELECTION.TECHNICAL_PROFESSIONAL_ABILITY")

        then: "CriterionName element"
        request.Criterion[idx].Name.text() == "Technicians or technical bodies for quality control"

        then: "CriterionDescription element"
        request.Criterion[idx].Description.text() == "The economic operator can call upon the following technicians or technical bodies , especially those responsible for quality control. For technicians or technical bodies not belonging directly to the economic operator's undertaking but on whose capacities the economic operator relies as set out under Part II, Section C, separate ESPD forms must be filled in."

        then: "CriterionLegislationReference element"
        checkLegislationReference(request, idx, "58(4)")

        then: "check all the sub groups"
        request.Criterion[idx].RequirementGroup.size() == 2

        then: "main sub group"
        request.Criterion[idx].RequirementGroup[0].ID.text() == "9e2fd892-80e2-4c13-98bb-1515e56f45af"
        request.Criterion[idx].RequirementGroup[0].RequirementGroup.size() == 0
        request.Criterion[idx].RequirementGroup[0].Requirement.size() == 1

        then: "main sub group requirements"
        def r1_0 = request.Criterion[idx].RequirementGroup[0].Requirement[0]
        checkRequirement(r1_0, "3aaca389-4a7b-406b-a4b9-080845d127e7", "Please specify", "DESCRIPTION")

        then: "info available electronically sub group"
        checkInfoAvailableElectronicallyRequirementGroup(request.Criterion[idx].RequirementGroup[1])
    }

    def "check the 'Please specify' requirements response"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true,
                specify: "technicians description"))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[0]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Description.text() == "technicians description"
    }

    def "check the 'Is this information available electronically' requirement response"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: false)))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[0]
        req.Response.size() == 1
        req.Response[0].Indicator.text() == "false"
    }

    def "check the 'Info electronically URL' requirement response"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, url: "http://hodor_16.com")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[1]
        req.Response.size() == 1
        req.Response[0].Evidence.EvidenceDocumentReference.Attachment.ExternalReference.URI.text() == "http://hodor_16.com"
    }

    def "check the 'Info electronically code' requirement response"() {
        given:
        def espd = new EspdDocument(techniciansTechnicalBodies: new TechnicalProfessionalCriterion(exists: true,
                availableElectronically: new AvailableElectronically(exists: true, code: "HODOR_16")))

        when:
        def request = parseResponseXml(espd)
        def idx = 0

        then:
        def subGroup = request.Criterion[idx].RequirementGroup[1]

        def req = subGroup.Requirement[2]
        req.Response.size() == 1
        req.Response[0].Code.text() == "HODOR_16"
    }

}