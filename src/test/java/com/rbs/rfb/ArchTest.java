package com.rbs.rfb;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.rbs.rfb");

        noClasses()
            .that()
                .resideInAnyPackage("com.rbs.rfb.service..")
            .or()
                .resideInAnyPackage("com.rbs.rfb.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.rbs.rfb.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
