#import "AdIntegrator.h"
#import <Appodeal/Appodeal.h>

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

- (void)initAds{
    [Appodeal initializeWithApiKey:@"youkeyhere" types:AppodealAdTypeInterstitial|AppodealAdTypeBanner|AppodealAdTypeRewardedVideo hasConsent:NO];
    
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
    [Appodeal showAd:AppodealShowStyleInterstitial rootViewController:(UIViewController*)[[[[UIApplication sharedApplication] delegate] window] rootViewController]];

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

