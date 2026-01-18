# 🌌 Sistema Solar Interativo em Java

![Java](https://img.shields.io/badge/Java-17%2B-007396?logo=java&logoColor=white)
![Swing](https://img.shields.io/badge/GUI-Swing-4D4D4D?logo=swing&logoColor=white)
![Graphics2D](https://img.shields.io/badge/Render-Graphics2D-6A0DAD?logo=opengl&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-blue)
![Status](https://img.shields.io/badge/Status-Complete-success)

## ✨ Sobre o Projeto
Um simulador visual interativo do Sistema Solar desenvolvido em Java Swing com física orbital realista, informações científicas detalhadas e interface gráfica intuitiva.

## 🚀 Funcionalidades
- **Simulação realista** dos 8 planetas do Sistema Solar
- **Informações científicas** precisas (distâncias, períodos orbitais, etc.)
- **Controles interativos** de velocidade (0.01x a 20x)
- **Visualizações gráficas** avançadas com estrelas piscantes e trilhas orbitais e anéis de Saturno em 3D
- **Painel de informações** em tempo real para cada planeta com base nas leis de Kepler

## 🎮 Como Executar

### Pré-requisitos
- Java JDK 17 ou superior
- Git (opcional)

### Compilação e Execução
```
# 1. Clone o repositório:
git clone https://github.com/seu-usuario/sistema-solar-java.git
cd sistema-solar-java

# 2. Compile o programa:
javac SolarSystem.java

# 3. Execute a aplicação:
java SolarSystem
```
### Executando no Eclipse
```
# 1. Importe o projeto como "Java Project"

# 2. Execute a classe SolarSystem.java

# 3. Clique na janela para ativar controles
```
## 🎮 Como Usar
Controles do Teclado:

↑ (Seta Up): Aumenta velocidade (×1.5)

↓ (Seta Down): Diminui velocidade (÷1.5)

→ (Seta Right): +0.5 de velocidade

← (Seta Left): -0.5 de velocidade

Espaço: Pausa/Continua

R: Reset velocidade para 1x

## 🖥️ Interface Gráfica
Painel Esquerdo: Selecione planetas

Painel Central: Visualização do sistema solar

Painel Direito: Informações científicas

Botões: Controles de velocidade

## 🏗️ Estrutura do Código
```text
SolarSystem/
├── SolarSystem.java            # Classe principal
├── CelestialBody (inner class) # Representa corpos celestes
├── drawSolarSystem()           # Renderização gráfica
├── createButtonPanel()         # Interface de controles
├── createInfoPanel()           # Painel de informações
├── initializeSolarSystem()     # Configuração inicial
└── updateSolarSystem()         # Atualização da simulação
```
## 🔧 Personalização
### Modificando Velocidades
```
java
// Em initializeSolarSystem(), ajuste os baseSpeed:
CelestialBody earth = new CelestialBody("Terra", 200, 10, Color.BLUE, 0.02);
//Velocidade base ↑
```
### Adicionando Novos Planetas
```
java
// Adicione após os planetas existentes:
CelestialBody novoPlaneta = new CelestialBody(
    "Nome",        // Nome
    700,           // Raio orbital
    12,            // Tamanho
    Color.RED,     // Cor
    0.002          // Velocidade
);
bodies.add(novoPlaneta);
```
### Alterando Cores
```
java
// Use qualquer cor do Java:
new Color(R, G, B)           // RGB (0-255)
new Color(R, G, B, alpha)    // RGBA com transparência
Color.HSBtoRGB(hue, sat, bri) // Cores HSB
```
## 🚀 Recursos Futuros (To-Do)
- [ ] Adicionar modo noturno/galáxia

- [ ] Implementar órbitas elípticas

- [ ] Adicionar cometas com caudas

- [ ] Criar sistema de zoom

- [ ] Adicionar constelações

- [ ] Exportar imagens do sistema

- [ ] Suporte a múltiplos sistemas solares

## 🤝 Contribuindo
### Contribuições são bem-vindas! Siga estes passos:

1. Fork o projeto

2. Crie uma branch para sua feature (git checkout -b feature/AmazingFeature)

3. Commit suas mudanças (git commit -m 'Add some AmazingFeature')

4. Push para a branch (git push origin feature/AmazingFeature)

5. Abra um Pull Request

## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE.txt) para detalhes.

## 👨‍💻 Autor
Guilherme Neto

GitHub: [@guilhermeNetogit](https://github.com/guilhermeNetogit)  
LinkedIn: [Guilherme Neto](https://www.linkedin.com/in/guilherme-roberto-neto-bbb305164/)

## 📞 Suporte
Encontrou um bug ou tem uma sugestão? Abra uma issue no GitHub.

##⭐ Se você gostou deste projeto, dê uma estrela no GitHub! ⭐
