Feature: Añadir diagnóstico y tratamiento
  Como médico
  Quiero añadir diagnóstico y tratamiento al paciente
  Para registrar la evolución clínica

  Scenario Outline: Añadir diagnóstico y tratamiento
    Given el médico está en el perfil del paciente
    When añade diagnóstico "<diagnostico>" y plan de tratamiento "<tratamiento>" y guarda
    Then el sistema confirma la adición y actualiza la historia clínica

    Examples:
      | diagnostico               | tratamiento            |
      | Hipotiroidismo leve       | Levotiroxina 50mcg/día|
      | Síndrome metabólico       | Dieta y ejercicio      |

  Scenario: Fallo al añadir sin datos
    Given el médico está en el perfil del paciente
    When intenta guardar sin llenar diagnóstico o tratamiento
    Then el sistema muestra error "Campos obligatorios incompletos"
