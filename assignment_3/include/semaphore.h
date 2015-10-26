/**
 * Marshall Bowers
 * Dr. Bin Lu
 * CSC331-80
 * 16 November 2015
 * semaphore.h
 */

#pragma once

#ifdef __APPLE__
#include <dispatch/dispatch.h>
#else
#include <semaphore.h>
#endif

typedef struct rk_sema {
#ifdef __APPLE__
    dispatch_semaphore_t sem;
#else
    sem_t sem;
#endif
} rk_sema_t;

static inline int rk_sema_init(struct rk_sema *s, uint32_t value) {
    int result;

#ifdef __APPLE__
    dispatch_semaphore_t *sem = &s->sem;

    *sem = dispatch_semaphore_create(value);

    result = 0;
#else
    result = sem_init(&s->sem, 0, value);
#endif

    return result;
}

static inline void rk_sema_wait(struct rk_sema *s) {
#ifdef __APPLE__
    dispatch_semaphore_wait(s->sem, DISPATCH_TIME_FOREVER);
#else
    int r;

    do {
        r = sem_wait(&s->sem);
    } while (r == -1 && errno == EINTR);
#endif
}

static inline void rk_sema_post(struct rk_sema *s) {
#ifdef __APPLE__
    dispatch_semaphore_signal(s->sem);
#else
    sem_post(&s->sem);
#endif
}
