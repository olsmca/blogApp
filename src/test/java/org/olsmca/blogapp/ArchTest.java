package org.olsmca.blogapp;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("org.olsmca.blogapp");

        noClasses()
            .that()
            .resideInAnyPackage("org.olsmca.blogapp.service..")
            .or()
            .resideInAnyPackage("org.olsmca.blogapp.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..org.olsmca.blogapp.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
