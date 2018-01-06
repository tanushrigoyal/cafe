'use strict';

import gulp from 'gulp';
import babel from 'gulp-babel';
import sourceMaps from 'gulp-sourcemaps';
import plumber from 'gulp-plumber';
import notify from 'gulp-notify';
import nodemon from 'gulp-nodemon';
import config from './server/config/local';

const dirs = {
    
            src: 'server/**/*.js',
            dest: 'build',
            maps: {
                src: `maps`,
                conf: {
                    includeContent: false,
                    sourceRoot: `../server`
                }
            }
};
 
gulp.task(`js:server`, () => {
    return gulp.src(dirs.src)
        .pipe(plumber({ errorHandler: notify.onError("Error: <%= error.message %>") }))
        .pipe(sourceMaps.init())
        .pipe(babel())
        .pipe(sourceMaps.write(dirs.maps.src, dirs.maps.conf))
        .pipe(gulp.dest(dirs.dest));
});

let taskList = [
    `js:server`
];


gulp.task(`watch`, () => {
    gulp.watch('server/**/*.js', [`js:server`]);
});


gulp.task('nodemon',taskList, () => {
    let init = false;
    return nodemon({
        script: `build/`,
        watch: 'build',
        ext: "js",
        env: {
            NODE_ENV: config.env,
            PORT: config.port
        }
    }).on('start', () => {
        if (!init) {
            init = true;
            gulp.start('watch');
        }
    });
});

gulp.task(`default`, [`nodemon`]);