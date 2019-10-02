#import "AdIntegrator.h"


@implementation AdIntegrator

+ (id)shared{
    static AdIntegrator* integrator = nil;
    @synchronized(self){
        if(integrator == nil){
            integrator = [[self alloc] init];
        }
    }
    return integrator;
}

bool isBannerUp = NO;

#pragma mark Core Methods

- (void)rewardedVideoWillDismissAndWasFullyWatched:(BOOL)wasFullyWatched{
    
    if (wasFullyWatched) {
        PTAdController::shared()->rewardedVideoDidEnd();
    }
    
}

- (void)initAds{
    [Appodeal setRewardedVideoDelegate:self];
}


-(void)showBanner{
    
    [Appodeal showAd:AppodealShowStyleBannerBottom rootViewController:(UIViewController*)[[[[UIApplication sharedApplication] delegate] window] rootViewController]];
    
    isBannerUp = YES;
}

-(void)hideBanner{
    [Appodeal hideBanner];
    isBannerUp = NO;
}

-(bool)isBannerVisible{
    return isBannerUp;
}

-(bool)isRewardedVideoAvialable{
    return [Appodeal isReadyForShowWithStyle: AppodealShowStyleRewardedVideo];
}

-(void)showInterstitial{
    if (appDelegate.shouldShowInterstitial){
        [Appodeal showAd:AppodealShowStyleInterstitial rootViewController:(UIViewController*)[[[[UIApplication sharedApplication] delegate] window] rootViewController]];
    }
    
}

-(void)showRewardedVideo{
    [Appodeal showAd:AppodealShowStyleRewardedVideo rootViewController:(UIViewController*)[[[[UIApplication sharedApplication] delegate] window] rootViewController]];

}

-(void)buttonActivated:(NSString*) name{

}
-(bool)buttonVisible:(NSString*)name{
    return true;
}
#pragma mark Integration

@end

