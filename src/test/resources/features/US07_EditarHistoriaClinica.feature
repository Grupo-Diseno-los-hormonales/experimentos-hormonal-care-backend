Feature: Editar historia clínica del paciente
  Como médico
  Quiero ingresar y actualizar información clínica
  Para mantener la historia clínica actualizada

  Scenario Outline: Editar historia clínica existente
    Given el médico está en "Historia clínica" del paciente
    When modifica o añade "<campo>" con "<valor>" y guarda
    Then el sistema actualiza y confirma el cambio

    Examples:
      | campo         | valor                                 |
      | Diagnóstico   | Hipotiroidismo                        |
      | Tratamiento   | Levotiroxina 75mcg/día                |
      | Evolución     | Mejorando                             |

  Scenario: Intentar guardar sin información
    Given el médico está en "Historia clínica"
    When intenta guardar sin llenar campos obligatorios
    Then el sistema muestra error "Complete los campos requeridos"