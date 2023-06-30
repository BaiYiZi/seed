#include <stdio.h>
#include <stdlib.h>

void generate(char *s) {
  char *end = s;

  for (int i = 0; i < 26; i++, end++) {
    *(s + i) = 'a' + i;
  }

  *end = '\0';
}

int main() {
  char *s = malloc(sizeof(char) * 27);
  generate(s);

  while (*s++) {
    printf("addr: {%x}\nvalue: {\"%s\"}\nstartBit: {\"%c\"}\n\n", s - 1, s - 1, *(s - 1));
  }

  free(s - 27);
  return 0;
}