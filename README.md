# Trabalho I - Máquina de Estados

Disciplina: Inteligência Artificial e Ilusão de Inteligência em Jogos

## Como compilar e rodar

Requisito: JDK 17 ou superior.

Por padrão são executadas 49 horas de simulação com 1500 ms de intervalo. 

## Agentes e estados

| Agente | Estados | Papel |
|---|---|---|
| Ferreiro (Agente B, classe Ferreiro) | Sleeping, Forging, WaitingOre, Selling | Dorme até o estoque atingir 12 minérios, forja espadas consumindo minério, vende-as na feira e volta a dormir quando o minério acaba e está de noite. |
| Mineiro (Agente A, classe Mineiro) | Sleeping, Mining, Repairing | Acorda às 05:00 para minerar, desgasta a picareta, conserta quando necessário e vai dormir às 20:00 guardando o estoque. |

## Comunicação entre os agentes

A comunicação acontece nos dois sentidos:

1. Ao sair de `Mining` (ou ao ir dormir em `Sleeping`), o Mineiro guarda o minério da mochila no estoque (`stock`). O Ferreiro acorda e volta a forjar quando o estoque atinge a cota de 12 minérios (`refill()`), zerando o estoque do Mineiro.
2. Se o Ferreiro fica sem minério durante o expediente, ele entra em `WaitingOre` e envia um pedido urgente (`oreRequested = true`).
3. Ao ver o pedido, o Mineiro altera seu comportamento: em vez de esperar a mochila atingir 12, ele sai de `Mining` com pelo menos 6 de minério para abastecer o Ferreiro. O pedido é limpo assim que o Ferreiro recolhe o minério.

## Como observar as transições

Cada hora imprime `---------- HORA HH:00 ----------`, seguido dos status do Ferreiro e do Mineiro. A troca de estado exibe a mensagem de saída (`leave`) do estado anterior e a de entrada (`enter`) do novo estado, por exemplo:

MINEIRO acordou!                                        (saída de Sleeping)
Vamos minerar!                                          (entrada em Mining)

## Estrutura

src/   código-fonte (State, Agent, Ferreiro, Mineiro, Sleeping, os estados de trabalho, AgentManager e Main)
docs/  diagramas e PDF