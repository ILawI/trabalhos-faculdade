#include <stdio.h>
#include <conio.h>
#include <stdlib.h>

int main()
{
int x=1; // ponto de partida
int qp = 0;
int sexo, id;
while (x<=3) // ponto de parada
{
	printf ("Entre com o sexo \n");
	scanf ("%d", & sexo);
	printf ("Entre idade \n");
	scanf ("%d", &id);
	if ((sexo==1)&&(id>25))
{
	qp++; // incremento de pessoas com essa caracteristica
	}	
	
	x++; // inc de pessoas
}
printf("O resultado:");
printf("%d\n" ,qp);
}

