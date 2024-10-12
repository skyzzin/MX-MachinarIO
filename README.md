# Skyzzin Generators Plugin

Skyzzin Generators é um plugin para Minecraft que permite criar geradores de itens customizados no mundo do jogo. Os geradores podem ser configurados para produzir diferentes tipos de materiais, como ferro, ouro e diamante, e podem ser colocados em locais específicos pelos jogadores. No futuro, o plugin terá uma funcionalidade adicional de "Agentes" que irão executar ações como quebrar blocos, pescar, coletar madeira e muito mais.

![](https://github.com/skyzzin/MX-MachinarIO/tree/master/mdfiles/1.png)

## Funcionalidades

- **Geradores de Itens:**
  - Crie geradores de diferentes materiais, configuráveis via arquivo YML.
  - Os geradores produzem itens no local especificado e impedem que outros jogadores peguem os itens diretamente.
  - Possibilidade de abrir um inventário associado ao gerador para coletar os itens produzidos.

- **Interações com o Mundo:**
  - Clique com o botão direito em um bloco do tipo "spawner" para criar um gerador, desde que o item em sua mão corresponda ao tipo configurado no arquivo YML.
  - Clique com o botão esquerdo para destruir um gerador ativo.
  - Abra o inventário do gerador clicando novamente com o botão direito no bloco "spawner".

## Futuras Funcionalidades

- **Agentes:**
  - Agentes serão máquinas que podem executar diferentes tarefas automaticamente:
    - **Quebra de blocos:** Agentes poderão minerar blocos específicos, como pedra e minérios.
    - **Pesca:** Agentes serão capazes de pescar automaticamente, coletando peixes e outros itens do mar.
    - **Coleta de madeira:** Agentes poderão cortar árvores e coletar madeira.
  - Cada agente terá configurações personalizáveis e poderá ser ativado/desativado pelos jogadores.

## Instalação

1. Baixe o arquivo `.jar` do plugin e coloque-o na pasta `plugins` do seu servidor Minecraft.
2. Inicie o servidor para gerar os arquivos de configuração automaticamente na pasta `plugins/SkyzzinGenerators`.
3. Edite o arquivo `config.yml` para personalizar os geradores conforme necessário.
4. Reinicie o servidor para aplicar as configurações.

## Configuração

O arquivo `config.yml` contém as configurações para os geradores. Exemplo de configuração:

```yaml
Generators:
  IronGenerator:
    type: "iron_ingot"
    title: "Gerador De Ferro"
    time: 10
  GoldGenerator:
    type: "gold_ingot"
    title: "Gerador De Ouro"
    time: 20
  DiamondGenerator:
    type: "diamond"
    title: "Gerador De Diamante"
    time: 30
