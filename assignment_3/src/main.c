/**
 * Marshall Bowers
 * Dr. Bin Lu
 * CSC331-80
 * 16 November 2015
 * main.c
 */

#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <pthread.h>

#include "error.h"
#include "semaphore.h"
#include "buffer.h"

buffer_item buffer[BUFFER_SIZE];
pthread_mutex_t mutex;
rk_sema_t empty;
rk_sema_t full;

int main(int argc, const char *argv[]) {
    if (argc != 4) {
        error_and_die("Usage: <sleep_duration> <producer_count> <consumer_count>");
    }

    int sleep_duration = atoi(argv[1]);

    int producer_count = atoi(argv[2]);

    int consumer_count = atoi(argv[3]);

    printf("%d\n", pthread_mutex_init(&mutex, NULL));
    printf("%d\n", rk_sema_init(&empty, 5));
    printf("%d\n", rk_sema_init(&full, 0));

    srand(time(0));

    for (int i = 0; i < producer_count; i++) {
        pthread_t thread_id;
        pthread_attr_t thread_attributes;

        pthread_attr_init(&thread_attributes);

        pthread_create(&thread_id, &thread_attributes, producer, NULL);
    }

    for (int i = 0; i < consumer_count; i++) {
        pthread_t thread_id;
        pthread_attr_t thread_attributes;

        pthread_attr_init(&thread_attributes);

        pthread_create(&thread_id, &thread_attributes, consumer, NULL);
    }

    sleep(sleep_duration);

    return 0;
}
